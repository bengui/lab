package com.benguiman.lab.ui.third_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.benguiman.lab.R
import com.benguiman.lab.ui.MainActivityComponentProvider
import com.benguiman.lab.ui.users_commons.UsersAdapter
import javax.inject.Inject

class ThirdFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: ThirdScreenViewModel
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivityComponentProvider)
            .getMainActivityComponent()
            .inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ThirdScreenViewModel::class.java)

        view?.let { view ->
            view.findViewById<Button>(R.id.load_users_btn).setOnClickListener {
                viewModel.loadUsers()
            }

            view.findViewById<Button>(R.id.clear_btn).setOnClickListener {
                viewModel.clearUsers()
            }

            usersAdapter = UsersAdapter()

            view.findViewById<RecyclerView>(R.id.users_list_recycler_view).apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(activity)
                adapter = usersAdapter
            }
        }

        viewModel.users.observe(this, Observer {
            usersAdapter.userList = it
            usersAdapter.notifyDataSetChanged()
        })

        viewModel.errorBannerMessage.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
    }

}