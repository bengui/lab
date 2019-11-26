package com.benguiman.lab.ui.second_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.benguiman.lab.R
import com.benguiman.lab.ui.MainActivityComponentProvider
import com.benguiman.lab.ui.users_commons.UsersAdapter
import javax.inject.Inject

class SecondFragment : Fragment() {
    @Inject
    lateinit var presenter: SecondPresenter
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usersAdapter = UsersAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivityComponentProvider).getMainActivityComponent()
            .secondFragmentComponentBuilder()
            .build()
            .inject(this)

        viewManager = LinearLayoutManager(activity)

        view?.let { view ->
            view.findViewById<RecyclerView>(R.id.user_list_recycler_view).apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = usersAdapter
            }
            view.findViewById<Button>(R.id.button_first_fragment).setOnClickListener {
                presenter.secondFragmentButtonClick()
            }
            view.findViewById<Button>(R.id.load_users_button).setOnClickListener {
                presenter.displayUserList()
            }
            view.findViewById<Button>(R.id.clear_button).setOnClickListener {
                presenter.clearUserList()
            }
            view.findViewById<Button>(R.id.error_button).setOnClickListener {
                presenter.displayError()
            }
        }

        presenter.userUiLiveData.observe(this, Observer {
            usersAdapter.userList = it
            usersAdapter.notifyDataSetChanged()
        })
        presenter.errorBannerMessage.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
    }
}