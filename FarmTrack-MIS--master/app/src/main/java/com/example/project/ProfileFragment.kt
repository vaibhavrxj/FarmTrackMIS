package com.example.project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    private lateinit var tvUserName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPhone: TextView
    private lateinit var ivProfilePic: ImageView
    private lateinit var btnEditProfile: Button
    private lateinit var btnLogout: Button
    private lateinit var ratingBar: RatingBar
    private lateinit var btnSubmitRating: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)

        tvUserName = rootView.findViewById(R.id.tvUserName)
        tvEmail = rootView.findViewById(R.id.tvUserEmail)
        tvPhone = rootView.findViewById(R.id.tvUserPhone)
        ivProfilePic = rootView.findViewById(R.id.ivProfilePic)
        btnEditProfile = rootView.findViewById(R.id.btnEditProfile)
        btnLogout = rootView.findViewById(R.id.btnLogout)
        ratingBar = rootView.findViewById(R.id.ratingBar)
        btnSubmitRating = rootView.findViewById(R.id.btnSubmitRating)

        loadProfileData()

        btnEditProfile.setOnClickListener {
            startActivity(Intent(activity, EditProfileActivity::class.java))
        }

        btnLogout.setOnClickListener {
            val sharedPref = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            sharedPref.edit().clear().apply()
            Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()

        }

        btnSubmitRating.setOnClickListener {
            val rating = ratingBar.rating
            Toast.makeText(requireContext(), "Thanks for rating us $rating stars!", Toast.LENGTH_SHORT).show()
        }

        return rootView
    }

    override fun onResume() {
        super.onResume()
        loadProfileData()
    }

    private fun loadProfileData() {
        val sharedPref = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        tvUserName.text = sharedPref.getString("name", "Guest User")
        tvEmail.text = sharedPref.getString("email", "Not Available")
        tvPhone.text = sharedPref.getString("phone", "Not Available")

        val profilePicUri = sharedPref.getString("profilePicUri", null)
        if (!profilePicUri.isNullOrEmpty()) {
            val resourceId = resources.getIdentifier(profilePicUri, "drawable", activity?.packageName)
            if (resourceId != 0) {
                ivProfilePic.setImageResource(resourceId)
            } else {
                ivProfilePic.setImageResource(R.drawable.ic_user_profile)
            }
        } else {
            ivProfilePic.setImageResource(R.drawable.ic_user_profile)
        }
    }
}
