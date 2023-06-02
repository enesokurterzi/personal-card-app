package com.example.PersonalCardApp.ui.school

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.PersonalCardApp.adapter.PersonalCardAdapter
import com.example.PersonalCardApp.configs.Util
import com.example.PersonalCardApp.databinding.FragmentSchoolBinding
import com.example.PersonalCardApp.models.PersonalCard
import com.example.PersonalCardApp.ui.detail.DetailActivity

class SchoolFragment : Fragment() {

    private var _binding: FragmentSchoolBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var mActivity: AppCompatActivity
    private lateinit var adapter: PersonalCardAdapter
    private lateinit var schoolViewModel: SchoolViewModel

    var schoolCards = mutableListOf<PersonalCard>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSchoolBinding.inflate(inflater, container, false)
        val root: View = binding.root

        schoolViewModel = ViewModelProvider(this).get(SchoolViewModel::class.java)

        schoolViewModel.getByGroup(requireContext())

        schoolViewModel.schoolCards.observe(viewLifecycleOwner) {
            adapter.clear()
            adapter.addAll(it)
            adapter.notifyDataSetChanged()
        }
        adapterSet()

        binding.schoolListView.setOnItemClickListener { adapterView, view, i, l ->
            Util.chosen = schoolCards[i]
            val intent = Intent(requireContext(), DetailActivity::class.java)
            startActivity(intent)
        }

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
        schoolViewModel.getByGroup(requireContext())
    }

    private fun adapterSet() {
        adapter = PersonalCardAdapter(mActivity, schoolCards, "School")
        binding.schoolListView.adapter = adapter
    }

}