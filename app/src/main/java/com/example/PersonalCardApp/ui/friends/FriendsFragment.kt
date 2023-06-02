package com.example.PersonalCardApp.ui.friends

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
import com.example.PersonalCardApp.databinding.FragmentFriendsBinding
import com.example.PersonalCardApp.models.PersonalCard
import com.example.PersonalCardApp.ui.detail.DetailActivity

class FriendsFragment : Fragment() {

    private var _binding: FragmentFriendsBinding? = null
    private val binding get() = _binding!!

    private lateinit var friendsViewModel: FriendsViewModel
    private lateinit var mActivity: AppCompatActivity
    private lateinit var adapter: PersonalCardAdapter

    var friendsCards = mutableListOf<PersonalCard>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFriendsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        friendsViewModel = ViewModelProvider(this).get(FriendsViewModel::class.java)

        friendsViewModel.getByGroup(requireContext())

        friendsViewModel.friendsCards.observe(viewLifecycleOwner) {
            adapter.clear()
            adapter.addAll(it)
            adapter.notifyDataSetChanged()
        }
        adapterSet()

        binding.friendsListView.setOnItemClickListener { adapterView, view, i, l ->
            Util.chosen = friendsCards[i]
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
        friendsViewModel.getByGroup(requireContext())
    }

    private fun adapterSet() {
        adapter = PersonalCardAdapter(mActivity, friendsCards, "Friends")
        binding.friendsListView.adapter = adapter
    }

}