package com.example.androidexpert2.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.androidexpert2.core.domain.usecase.RecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(recipeUseCase: RecipeUseCase): ViewModel() {
    val recipes = recipeUseCase.getRecipes().asLiveData()
}