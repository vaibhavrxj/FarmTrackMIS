package com.example.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment

class SchemeFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var schemeList: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_scheme, container, false)

        listView = rootView.findViewById(R.id.lvSchemes)

        schemeList = arrayListOf(
            "Kisan Credit Card (KCC)",
            "Pradhan Mantri Fasal Bima Yojana (PMFBY)",
            "National Agriculture Market (eNAM)",
            "Pradhan Mantri Krishi Sinchayee Yojana (PMKSY)",
            "Soil Health Management",
            "Rashtriya Krishi Vikas Yojana (RKVY)",
            "Paramparagat Krishi Vikas Yojana (PKVY)",
            "Micro Irrigation Fund (MIF)",
            "Sub-Mission on Agricultural Mechanization (SMAM)",
            "National Mission on Sustainable Agriculture (NMSA)"
        )

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, schemeList)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val schemeName = schemeList[position]
            Toast.makeText(requireContext(), schemeName, Toast.LENGTH_SHORT).show()
        }

        return rootView
    }
}
