package com.example.synchronyproject.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.example.synchronyproject.*
import models.CountryInfo

class DashboardFragment : Fragment() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: CountriesAdapter
    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        mRecyclerView = root.findViewById(R.id.recyclerView)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.addItemDecoration(DividerItemDecoration(root.context, HORIZONTAL))
        mLayoutManager = LinearLayoutManager(root.context)

        val countries = ArrayList<CountryInfo>()
        mAdapter = CountriesAdapter(countries)

        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = mAdapter

        mAdapter.setOnItemClickListener { position ->
            run {
                val intent = Intent(root.context, CountryDisplay::class.java)
                intent.putExtra("COUNTRY", countries[position])
                startActivity(intent)
            }
        }

        retrieveCountries(countries)

        return root
    }

    private fun retrieveCountries(countries: ArrayList<CountryInfo>)
    {
        val service = ServiceBuilder.buildService(CountryAPI::class.java)
        var call = service.getCountryList()

        call.enqueue(object: Callback<ArrayList<CountryInfo>> {
            override fun onResponse(call: Call<ArrayList<CountryInfo>>, response: Response<ArrayList<CountryInfo>>) {
                if (response.isSuccessful && response.body() != null)
                {
                    for (country in response.body()!!)
                    {
                        countries.add(country)
                        mAdapter.notifyItemInserted(countries.indexOf(country))
                    }
                    Log.d("Successful request: ", response.body().toString())
                }
                else
                {
                    Log.d("Unsuccessful request", "null body")
                }
            }

            override fun onFailure(call: Call<ArrayList<CountryInfo>>, t: Throwable) {
                Log.d("Unsuccessful request: ", t.toString())
            }
        })
    }

}