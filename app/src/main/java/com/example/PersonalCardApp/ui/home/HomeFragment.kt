package com.example.PersonalCardApp.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.PersonalCardApp.adapter.PersonalCardAdapter
import com.example.PersonalCardApp.configs.Util
import com.example.PersonalCardApp.databinding.FragmentHomeBinding
import com.example.PersonalCardApp.models.PersonalCard
import com.example.PersonalCardApp.ui.detail.DetailActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: PersonalCardAdapter
    private lateinit var mActivity: AppCompatActivity
    private lateinit var homeViewModel: HomeViewModel

    var homeCards = mutableListOf<PersonalCard>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.getLastTen(requireContext())

        homeViewModel.homeCards.observe(viewLifecycleOwner) {
            adapter.clear()
            adapter.addAll(it)
            adapter.notifyDataSetChanged()
        }
        adapterSet()

        binding.HomeListView.setOnItemClickListener { adapterView, view, i, l ->
            Util.chosen = homeCards[i]
            val intent = Intent(requireContext(), DetailActivity::class.java)
            startActivity(intent)
        }

        binding.txtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                homeViewModel.search(requireContext(),p0.toString())
            }

        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onResume() {
        super.onResume()
        if (binding.txtSearch.text.toString().isNotEmpty()){
            homeViewModel.search(requireContext(), binding.txtSearch.text.toString())
        } else {
            homeViewModel.getLastTen(requireContext())
        }

    }

    private fun adapterSet() {
        adapter = PersonalCardAdapter(mActivity, homeCards, "home")
        binding.HomeListView.adapter = adapter
    }

}