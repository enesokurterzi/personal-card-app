package com.example.PersonalCardApp.ui.work

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.PersonalCardApp.adapter.PersonalCardAdapter
import com.example.PersonalCardApp.configs.Util
import com.example.PersonalCardApp.databinding.FragmentWorkBinding
import com.example.PersonalCardApp.models.PersonalCard
import com.example.PersonalCardApp.ui.detail.DetailActivity

class WorkFragment : Fragment() {

    private var _binding: FragmentWorkBinding? = null
    private val binding get() = _binding!!

    private lateinit var mActivity: AppCompatActivity
    private lateinit var adapter: PersonalCardAdapter
    private lateinit var workViewModel: WorkViewModel

    var workCards = mutableListOf<PersonalCard>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkBinding.inflate(inflater, container, false)
        val root: View = binding.root

        workViewModel = ViewModelProvider(this).get(WorkViewModel::class.java)

        workViewModel.getByGroup(requireContext())

        workViewModel.workCards.observe(viewLifecycleOwner) {
            adapter.clear()
            adapter.addAll(it)
            adapter.notifyDataSetChanged()
        }
        adapterSet()

        binding.workListView.setOnItemClickListener { adapterView, view, i, l ->
            Util.chosen = workCards[i]
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
        workViewModel.getByGroup(requireContext())
    }

    private fun adapterSet() {
        adapter = PersonalCardAdapter(mActivity, workCards, "Work")
        binding.workListView.adapter = adapter
    }

}