package com.furkansubasiay.marveltestproject.ui.herolist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.furkansubasiay.marveltestproject.R
import com.furkansubasiay.marveltestproject.databinding.FragmentCharacterListBinding
import com.furkansubasiay.marveltestproject.model.character.MarvelCharacterItem
import com.furkansubasiay.marveltestproject.ui.base.BaseFragment
import com.furkansubasiay.marveltestproject.ui.detail.CharacterDetailFragment
import com.furkansubasiay.marveltestproject.util.AnalyticsUtils
import com.furkansubasiay.marveltestproject.util.ItemClickListener
import com.furkansubasiay.marveltestproject.vm.CharactersViewModel
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject


class CharacterListFragment : BaseFragment(), ItemClickListener<MarvelCharacterItem> {


    lateinit var binding: FragmentCharacterListBinding
    var viewModel: CharactersViewModel? = null
    lateinit var listView: RecyclerView

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    var adapter : CharacterAdapter?=null
    var resultList= mutableListOf<MarvelCharacterItem>()
    val analyticsUtils = AnalyticsUtils()
    var rootView:View?=null

    companion object{
        private const val columnCount = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_character_list, container, false)
       rootView = binding.root
        init(rootView!!)
        return rootView
    }

    fun init(view:View){
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CharactersViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        listView = binding.characterList
        listView.layoutManager =GridLayoutManager(context, columnCount)
        listView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    showLoading(requireContext())

                    viewModel!!.fetchReposPaging(object :PagingListenerCallback{
                        override fun onMoreListenerFinished() {
                            hideLoading()
                        }
                    })
                }
            }
        })
        adapter = CharacterAdapter(resultList,this)
        listView.adapter=adapter
        if(resultList.isNullOrEmpty())
        {
            doApiCall()
        }
    }
    override fun onClick(item: MarvelCharacterItem) {
        analyticsUtils.logScreenViews(context!!, AnalyticsUtils.SCREEN_NAMES.marvelist,item.character_id)
        changeFragment(item)
    }

    fun doApiCall()
    {
        showLoading(requireContext())
        viewModel!!.results!!.observe(this, Observer { result ->

            hideLoading()
            if (result != null)
            {
                resultList.addAll(result.results)
                adapter!!.setList(resultList)
                adapter!!.notifyDataSetChanged()
            }

        })
    }

    private fun changeFragment(marvelCharacter: MarvelCharacterItem) {
        analyticsUtils.logScreenViews(context!!,AnalyticsUtils.SCREEN_NAMES.detail)
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frame_layout, CharacterDetailFragment.newInstance(marvelCharacter.character_id))
            .addToBackStack(null)
            .commit()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()

    }


}
