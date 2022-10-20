package com.hao.androidrecord.activity.physicslayout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ContributorsViewModel : ViewModel() {

    companion object {
        private const val REPO_USER = "Jawnnypoo"
        private const val REPO_NAME = "PhysicsLayout"
    }

    private val contributors by lazy {
        MutableLiveData<List<Contributor>>().also {
            loadContributors()
        }
    }

    fun getContributors(): LiveData<List<Contributor>> {
        return contributors
    }

    private fun loadContributors() {
        viewModelScope.launch {
            val newContributors = GitHubClient.instance().contributors(REPO_USER, REPO_NAME)
            contributors.value = newContributors
        }
    }
}
