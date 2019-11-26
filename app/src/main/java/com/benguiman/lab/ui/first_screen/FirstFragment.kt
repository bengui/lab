package com.benguiman.lab.ui.first_screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.benguiman.lab.R
import com.benguiman.lab.ui.MainActivityComponentProvider
import com.benguiman.lab.ui.UserUi
import com.benguiman.lab.ui.users_commons.UsersAdapter
import javax.inject.Inject

class FirstFragment : Fragment(), FirstView {

    private lateinit var usersAdapter: UsersAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    @Inject
    lateinit var firstPresenter: FirstPresenter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        (context as MainActivityComponentProvider).getMainActivityComponent()
            .firstFragmentComponentBuilder()
            .firstView(this)
            .build()
            .inject(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usersAdapter = UsersAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        viewManager = LinearLayoutManager(activity)
        view.findViewById<RecyclerView>(R.id.user_list_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = usersAdapter

        }
        view.findViewById<Button>(R.id.button_first_fragment).setOnClickListener {
            firstPresenter.secondFragmentButtonClick()
        }
        view.findViewById<Button>(R.id.load_users_button).setOnClickListener {
            firstPresenter.displayUserList()
        }
        view.findViewById<Button>(R.id.clear_button).setOnClickListener {
            firstPresenter.clearUserList()
        }
        return view
    }

    override fun printUserList(userList: List<UserUi>) {
        usersAdapter.userList = userList
        usersAdapter.notifyDataSetChanged()
    }

}