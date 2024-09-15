package com.example.androidexpert2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidexpert2.R
import com.example.androidexpert2.core.data.Result
import com.example.androidexpert2.core.domain.model.Recipe
import com.example.androidexpert2.core.ui.RecipeAdapter
import com.example.androidexpert2.databinding.FragmentHomeBinding
import com.example.androidexpert2.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvRecipe.layoutManager = layoutManager
        binding.rvRecipe.setHasFixedSize(true)

        homeViewModel.recipes.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Result.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val data = resource.data
                    if (data == null) {
                        return@observe
                    } else {
                        setDataa(data)
                    }
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.viewError.root.visibility = View.VISIBLE
                    binding.viewError.tvError.text =
                        resource.message ?: getString(R.string.somethingWrong)
                }
            }
        }
    }

    private fun setDataa(recipes: List<Recipe>) {
        val adapter = RecipeAdapter(recipes)
        adapter.setOnItemClickCallback(object : RecipeAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Recipe) {
                val action = HomeFragmentDirections.actionNavigationHomeToDetailActivity(
                    data.recipeId
                )
                findNavController().navigate(action)
            }
        })
        binding.rvRecipe.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}