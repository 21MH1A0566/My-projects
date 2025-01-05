package com.harsha.buildlinkbetweenadeptandcompanies.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.harsha.buildlinkbetweenadeptandcompanies.Models.Post
import com.harsha.buildlinkbetweenadeptandcompanies.R
import com.harsha.buildlinkbetweenadeptandcompanies.adapters.MyPostRvAdapter
import com.harsha.buildlinkbetweenadeptandcompanies.databinding.FragmentMyPostBinding
import com.harsha.buildlinkbetweenadeptandcompanies.databinding.FragmentPostBinding

class MyPostFragment : Fragment() {
    private lateinit var binding: FragmentMyPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentMyPostBinding.inflate(inflater, container, false)
        var postList=ArrayList<Post>()
        var adapter=MyPostRvAdapter(requireContext(),postList)
        binding.rv.layoutManager=StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter=adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
            var templist= arrayListOf<Post>()
            for (i in it.documents){
                var post:Post=i.toObject<Post>()!!
                templist.add(post)
            }
            postList.addAll(templist)
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    companion object {

    }
}