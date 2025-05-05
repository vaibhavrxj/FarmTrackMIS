package com.example.project

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    private lateinit var tvWelcomeMessage: TextView
    private lateinit var tvFarmerName: TextView
    private lateinit var tvFarmerStats: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        tvWelcomeMessage = rootView.findViewById(R.id.tvWelcomeMessage)
        tvFarmerName = rootView.findViewById(R.id.tvFarmerName)
        tvFarmerStats = rootView.findViewById(R.id.tvFarmerStats)

        val sharedPref = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val userName = sharedPref.getString("userName", "Farmer")

        tvWelcomeMessage.text = "🌾 Welcome to FarmTrack MIS 🌿"
        tvFarmerName.text = "👨‍🌾 Farmer: $userName"
        tvFarmerStats.text = "Total Farms: 5 | Total Crops: 20"

        val sections = listOf(
            Triple(R.id.farmOverviewLayout, "Farm Overview", "Crop Types: Corn, Wheat\nStatus: Healthy"),
            Triple(R.id.upcomingEventsLayout, "Upcoming Events", "Next Sowing Date: 10th May"),
            Triple(R.id.marketPriceLayout, "Market Price Updates", "Wheat: ₹25/kg | Corn: ₹30/kg"),
            Triple(R.id.governmentSchemesLayout, "Trending Government Schemes", "Scheme: PM-Kisan, Crop Insurance"),
            Triple(R.id.knowledgeBaseLayout, "Knowledge Base", "Tips for Natural Pest Management"),
            Triple(R.id.taskSchedulerLayout, "Upcoming Tasks Reminders", "Irrigation: Tomorrow\nFertilizer: Next Week")
        )

        for ((id, title, content) in sections) {
            val card = rootView.findViewById<View>(id)
            val tvTitle = card.findViewById<TextView>(R.id.tvSectionTitle)
            val tvContent = card.findViewById<TextView>(R.id.tvSectionContent)
            val btnAction = card.findViewById<Button>(R.id.btnAction)

            tvTitle.text = title
            tvContent.text = content

            if (id == R.id.governmentSchemesLayout) {
                btnAction.visibility = View.VISIBLE
                btnAction.text = "View Schemes"
                btnAction.setOnClickListener {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, SchemeFragment())
                        .addToBackStack(null)
                        .commit()
                }
            } else {
                btnAction.visibility = View.GONE
            }
        }
        return rootView
    }
}
