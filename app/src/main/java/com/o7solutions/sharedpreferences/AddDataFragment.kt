package com.o7solutions.sharedpreferences

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.o7solutions.sharedpreferences.databinding.FragmentAddDataBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [AddDataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddDataFragment : Fragment() {
    lateinit var binding: FragmentAddDataBinding
    lateinit var mainActivity: MainActivity
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor:SharedPreferences.Editor
     var userModel= UserModel()
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity=activity as MainActivity

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = FragmentAddDataBinding.inflate(layoutInflater)
        val sharedPreferences = context?.getSharedPreferences("pref", MODE_PRIVATE)
            binding.btnSave.setOnClickListener{
                if(binding.etName.text.isEmpty()){
                    binding.etName.error="Enter Your Name"
                }else if(binding.etEmail.text.isEmpty()){
                    binding.etEmail.error="Enter Your Email"
                }else if(binding.etPhoneNumber.text.isEmpty()){
                    binding.etPhoneNumber.error="Enter Your Number"
                }else if(binding.etPhoneNumber.text.toString().length < 10){
                    binding.etPhoneNumber.error="Enter 10 Digit Number"
                }else{
                    val editor= sharedPreferences?.edit()
                    val gson = Gson()
                    userModel=UserModel(binding.etName.text.toString(),binding.etEmail.text.toString(),binding.etPhoneNumber.text.toString())
                    println("UserModel"+userModel)
                    val user: String = gson.toJson(userModel)
                    editor?.putString("MyObject", user)
                    editor?.apply()
                    findNavController().navigate(R.id.action_addDataFragment_to_showDataFragment)
                }
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
         * @return A new instance of fragment AddDataFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddDataFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}




