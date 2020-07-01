package com.furkansubasiay.marveltestproject.ui.detail


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.furkansubasiay.marveltestproject.R
import com.furkansubasiay.marveltestproject.databinding.FragmentCharacterDetailBinding
import com.furkansubasiay.marveltestproject.ui.base.BaseFragment
import com.furkansubasiay.marveltestproject.ui.detail.comics.ComicsAdapter
import com.furkansubasiay.marveltestproject.vm.CharacterDetailViewModel
import com.like.LikeButton
import com.like.OnLikeListener


private const val CHARACTER_ID = "character_id"

class CharacterDetailFragment : BaseFragment() {

    private var characterId: Long? = null
    lateinit var binding: FragmentCharacterDetailBinding
    private lateinit var viewModel: CharacterDetailViewModel

    lateinit var listView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterId = it.getLong(
                CHARACTER_ID
            )

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_character_detail, container, false)
        val view = binding.root
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CharacterDetailViewModel::class.java)
        binding.viewModel = viewModel
        if (characterId != null) {
            viewModel.bindCharacterId(characterId)
        }
        binding.lifecycleOwner = this
        initRecyclerView(view)
        initLikeButton(view)
        return view
    }

    @SuppressLint("WrongConstant")
    private fun initRecyclerView(view: View) {
        listView = view.findViewById<RecyclerView>(R.id.comics_list)
        listView.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        fetchComics()
    }

    private fun fetchComics() {
        viewModel!!.resultsComics!!.observe(this, Observer { result ->
            if (result != null) {
                listView.adapter =
                    ComicsAdapter(
                        result!!.results
                    )
            }
        })
    }

    fun initLikeButton(view: View) {
        val likeButton = view.findViewById<LikeButton>(R.id.star_button)
        if(characterId==null)
        likeButton.visibility=View.GONE
        else {
            likeButton.visibility = View.VISIBLE
            viewModel.isFavorite!!.observe(this, Observer { result ->

                likeButton.setLiked(result)
            })

            likeButton.setOnLikeListener(object : OnLikeListener {
                override fun liked(likeButton: LikeButton) {
                    viewModel.bindLikeButtonValue(true)
                }

                override fun unLiked(likeButton: LikeButton) {
                    viewModel.bindLikeButtonValue(false)
                }
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(characterId: Long) =
            CharacterDetailFragment().apply {
                arguments = Bundle().apply {
                    putLong(CHARACTER_ID, characterId)
                }
            }
    }
}
