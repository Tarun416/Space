package com.example.spacex.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.model.ItemDomain
import com.example.spacex.utils.SpacesItemDecoration
import com.example.spacex.R
import com.example.spacex.databinding.FragmentListBinding
import com.example.spacex.ui.MainActivity
import com.example.spacex.ui.details.DetailsFragment
import com.example.spacex.utils.ItemState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListFragment : Fragment(), ListAdapter.OnPicClick {

    private lateinit var binding: FragmentListBinding
    private val viewModel: ListViewModel by viewModels()
    private var pictureslist = ArrayList<ItemDomain>()
    private lateinit var adapter: ListAdapter

    private lateinit var onClickListener: OnClickListener

    companion object {
        fun newInstance() = ListFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onClickListener = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRv()
        initViewModel()
    }

    private fun setRv() {
        adapter = ListAdapter(requireActivity(), this, pictureslist)
        binding.picturerv.layoutManager = GridLayoutManager(requireActivity(), 2)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.margin_8)
        binding.picturerv.addItemDecoration(SpacesItemDecoration(spacingInPixels))
        binding.picturerv.adapter = adapter
    }


    override fun onPicClick(pos: Int) {
        onClickListener.onPicClick(pictureslist[pos])
    }


    private fun initViewModel() {
        viewModel.liveData.observe(requireActivity()) {
            when (it) {
                ItemState.ShowLoading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                ItemState.HideLoading -> {
                    binding.progressbar.visibility = View.GONE
                }
                ItemState.Empty -> { /* show empty state */
                    if (pictureslist.isEmpty())
                        binding.emptyView.visibility = View.VISIBLE
                }
                is ItemState.Success -> {
                    binding.emptyView.visibility = View.GONE
                    handleData(it.list)
                }
                is ItemState.Error -> {
                    handleError(it.exception.message)
                }


            }
        }
        viewModel.getPictures()
    }

    private fun handleError(message: String?) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    private fun handleData(data: List<ItemDomain>?) {
        pictureslist.clear()
        pictureslist.addAll(data!!)
        pictureslist.reverse()
        adapter.notifyDataSetChanged()

    }

    interface OnClickListener {
        fun onPicClick(item: ItemDomain)
    }

}