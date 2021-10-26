package by.brust.task_5_cats.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.brust.task_5_cats.data.Cat
import by.brust.task_5_cats.databinding.CatsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatsFragment : Fragment() {
    private var binding: CatsFragmentBinding? = null
    private val viewModel by viewModels<CatsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = CatsFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val catAdapter = CatAdapter(object : CatAdapter.OnImageClickListener {
            override fun onItemClick(image: Cat) {
                val action = CatsFragmentDirections.actionCatsFragmentToDetailCatFragment(image)
                findNavController().navigate(action)
            }
        })
        binding?.apply {
            recycler.apply {
                val decoration =
                    DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
                addItemDecoration(decoration)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = catAdapter
            }
        }
        viewModel.allCatsImage.observe(viewLifecycleOwner) {
            catAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}
