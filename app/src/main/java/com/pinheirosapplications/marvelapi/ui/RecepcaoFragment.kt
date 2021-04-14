package com.pinheirosapplications.marvelapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pinheirosapplications.marvelapi.R
import com.pinheirosapplications.marvelapi.Sessao
import com.pinheirosapplications.marvelapi.services.JsonUtils
import kotlinx.android.synthetic.main.fragment_recepcao.*
import okhttp3.*
import java.io.IOException
import kotlin.system.exitProcess

class RecepcaoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recepcao, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonEntrar.setOnClickListener {
            getData()
        }

        buttonSair.setOnClickListener {
            exitProcess(0)
        }
    }

    fun getData() {
        val URLBase = "https://gateway.marvel.com/v1/public/characters?ts=1&apikey=fbfdcfcc50d87d045b50e4d6d4d33086&hash=b858744370fc595bdab60374dd9fbcf5"

        val request = Request.Builder().url(URLBase).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                val result = JsonUtils.getHeroList(json)
                Sessao.setHeroList(result)
                findNavController().navigate(R.id.heroListFragment)
            }
        })
    }
}