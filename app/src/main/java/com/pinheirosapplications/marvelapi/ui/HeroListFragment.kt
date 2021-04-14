package com.pinheirosapplications.marvelapi.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pinheirosapplications.marvelapi.R
import com.pinheirosapplications.marvelapi.Sessao
import com.pinheirosapplications.marvelapi.models.Hero
import com.pinheirosapplications.marvelapi.services.JsonUtils
import com.pinheirosapplications.marvelapi.ui.adapters.HerosAdapter
import kotlinx.android.synthetic.main.fragment_hero_list.*
import okhttp3.*
import java.io.IOException


class HeroListFragment : Fragment() {

    private lateinit var herosAdapter: HerosAdapter
    private var herosList: MutableList<Hero> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        herosAdapter = HerosAdapter(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hero_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hero_recyclerview.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        hero_recyclerview.setHasFixedSize(true)
        hero_recyclerview.adapter = herosAdapter

        herosAdapter.submitList(Sessao.getHeroList())
    }
}