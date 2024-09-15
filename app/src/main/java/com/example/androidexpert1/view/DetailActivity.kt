package com.example.androidexpert1.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.example.androidexpert1.R
import com.example.androidexpert1.core.data.Result
import com.example.androidexpert1.core.domain.model.Recipe
import com.example.androidexpert1.core.utils.htmlParser
import com.example.androidexpert1.core.utils.setImageViewTint
import com.example.androidexpert1.core.utils.setTextViewColor
import com.example.androidexpert1.databinding.ActivityDetailBinding
import com.example.androidexpert1.viewModel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val args: DetailActivityArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()
    private var favoriteStatus = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val recipeId: Int = args.id

        viewModel.getDetailRecipes(recipeId).observe(this) {
            when (it) {
                is Result.Loading -> {
                    binding.progressBarDetail.visibility = View.VISIBLE
                    binding.frameLayout.visibility = View.GONE
                    binding.constraintLayout.visibility = View.GONE
                }
                is Result.Success -> {
                    binding.viewError.root.visibility = View.GONE
                    binding.progressBarDetail.visibility = View.GONE
                    binding.frameLayout.visibility = View.VISIBLE
                    binding.constraintLayout.visibility = View.VISIBLE
                    setDataa(it.data)
                }
                is Result.Error -> {
                    binding.viewError.root.visibility = View.VISIBLE
                    binding.viewError.tvError.text =
                        it.message ?: getString(R.string.somethingWrong)
                    binding.progressBarDetail.visibility = View.GONE
                    binding.clDetail.visibility = View.GONE
                    binding.frameLayout.visibility = View.GONE
                }
                else -> Unit
            }
        }

        binding.fabBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setDataa(data: Recipe?) {
        data?.let {
            Glide.with(this)
                .load(data.image)
                .into(binding.ivDetail)
            binding.apply {
                tvDetailTitle.text = data.title
                setImageViewTint(applicationContext, ivDetailHealthy, data.veryHealthy.toString())
                setTextViewColor(applicationContext, tvDetailHealthy, data.veryHealthy.toString())
                setImageViewTint(applicationContext, ivDetailVegetarian, data.vegetarian.toString())
                setTextViewColor(applicationContext, tvDetailVegetarian, data.vegetarian.toString())
                setImageViewTint(applicationContext, ivDetailVegan, data.vegan.toString())
                setTextViewColor(applicationContext, tvDetailVegan, data.vegan.toString())
                setImageViewTint(applicationContext, ivDetailCheap, data.cheap.toString())
                setTextViewColor(applicationContext, tvDetailCheap, data.cheap.toString())
                setImageViewTint(applicationContext, ivDetailDairyFree, data.dairyFree.toString())
                setTextViewColor(applicationContext, tvDetailDairyFree, data.dairyFree.toString())
                setImageViewTint(applicationContext, ivDetailGlutenFree, data.glutenFree.toString())
                setTextViewColor(applicationContext, tvDetailGlutenFree, data.glutenFree.toString())
                tvDetailDescription.text = htmlParser(data.summary)
            }
            viewModel.getRecipesById(data.recipeId).observe(this@DetailActivity) {
                favoriteStatus = it.isFavorite == true
                setFavoriteStatus(favoriteStatus)
            }
            fabFavoriteClick(data)
        }
    }

    private fun fabFavoriteClick(data: Recipe) {
        binding.fabFavorite.setOnClickListener {
            favoriteStatus = !favoriteStatus
            viewModel.setFavoriteRecipes(data, favoriteStatus)
            setFavoriteStatus(favoriteStatus)
        }
    }

    private fun setFavoriteStatus(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_bookmark
                )
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_no_bookmark
                )
            )
        }
    }
}










