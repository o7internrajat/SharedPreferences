package com.o7solutions.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.o7solutions.sharedpreferences.databinding.FragmentShowDataBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [ShowDataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShowDataFragment : Fragment() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: FragmentShowDataBinding

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentShowDataBinding.inflate(layoutInflater)
        val preferences = context?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val show = Gson()
        val json: String? = preferences?.getString("MyObject", "")
        if (json?.isNotEmpty() == true) {
            val obj: UserModel = show.fromJson(json, UserModel::class.java)
            binding.tvName.text = obj.name
            binding.tvEmail.text = obj.email
            binding.tvPhone.text = obj.phone
        }
        return binding.root
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShowDataFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShowDataFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

