package com.evendred.basicapp.ui.champion.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import com.evendred.basicapp.R
import kotlinx.android.synthetic.main.fragment_champion_detail.*

class ChampionDetailFragment: Fragment() {
    companion object {
        private const val ARGUMENT_CHAMPION_ID = "argumentChampionId"

        fun getNavDirection(championId: String): NavDirections = object : NavDirections {
            override fun getArguments(): Bundle = getBundle(championId)
            override fun getActionId(): Int = R.id.action_championsListFragment_to_championsDetailFragment
        }

        private fun getBundle(championId: String) = Bundle().apply {
            putString(ARGUMENT_CHAMPION_ID, championId)
        }

        private fun getChampionId(arguments: Bundle?): String {
            return arguments?.getString(ARGUMENT_CHAMPION_ID) ?: throw Exception("Champion Id missing")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_champion_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text.text = getChampionId(arguments)
    }
}