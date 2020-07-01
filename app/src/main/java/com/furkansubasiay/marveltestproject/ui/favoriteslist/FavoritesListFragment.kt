package com.furkansubasiay.marveltestproject.ui.favoriteslist


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.furkansubasiay.marveltestproject.R
import com.furkansubasiay.marveltestproject.databinding.FragmentFavoritesListBinding
import com.furkansubasiay.marveltestproject.db.entity.MarvelCharacter
import com.furkansubasiay.marveltestproject.ui.base.BaseFragment
import com.furkansubasiay.marveltestproject.ui.detail.CharacterDetailFragment
import com.furkansubasiay.marveltestproject.util.AnalyticsUtils
import com.furkansubasiay.marveltestproject.vm.FavoritesViewModel
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject


class FavoritesListFragment : BaseFragment() {
    lateinit var binding: FragmentFavoritesListBinding
    var viewModel: FavoritesViewModel? = null
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    lateinit var listView: RecyclerView
    var constraintEmpty: View? = null
    var adapter: FavoritesAdapter? = null
    var resultList = mutableListOf<MarvelCharacter>()
    var rootView: View? = null
    companion object{
        private const val columnCount = 2
    }
    var analyticsUtils = AnalyticsUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favorites_list, container, false)
        rootView = binding.root
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(FavoritesViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        init()

        return rootView
    }

    @SuppressLint("WrongConstant")
    private fun init() {
        constraintEmpty = rootView!!.findViewById(R.id.rv_empty)
        constraintEmpty!!.visibility=View.GONE
        listView = rootView!!.findViewById<RecyclerView>(R.id.favorites_list)
        listView.layoutManager = GridLayoutManager(context, columnCount)
        adapter = FavoritesAdapter(resultList,
            object : FavoritesAdapter.OnItemClickListener {
                override fun onClick(marvelCharacter: MarvelCharacter) {

                    analyticsUtils.logScreenViews(context!!, AnalyticsUtils.SCREEN_NAMES.favoritelist,marvelCharacter.character_id)
                    changeFragment(marvelCharacter)

                }
            })
        listView.adapter=adapter
        if(resultList.isNullOrEmpty())
        getFavorites()
        else{
            constraintEmpty!!.visibility=View.GONE
            listView.visibility=View.VISIBLE
        }

    }

    private fun getFavorites() {
        showLoading(requireContext())
        viewModel!!.results!!.observe(this, Observer { result ->
            hideLoading()
            if (result != null) {
                if(result.size==0){
                    listView.visibility=View.GONE
                    constraintEmpty!!.visibility=View.VISIBLE
                }
                else{
                    constraintEmpty!!.visibility=View.GONE
                    listView.visibility=View.VISIBLE
                }
                resultList.clear()
                resultList.addAll(result)
                adapter!!.setList(resultList)
                adapter!!.notifyDataSetChanged()
            }
        }
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel!!.getFavorites()
    }

    private fun changeFragment(marvelCharacter: MarvelCharacter) {

        analyticsUtils.logScreenViews(context!!, AnalyticsUtils.SCREEN_NAMES.detail)
        requireActivity().supportFragmentManager.beginTransaction().replace(
            R.id.frame_layout,
            CharacterDetailFragment.newInstance(marvelCharacter.character_id)
        )
            .addToBackStack(null)
            .commit()
    }

}
