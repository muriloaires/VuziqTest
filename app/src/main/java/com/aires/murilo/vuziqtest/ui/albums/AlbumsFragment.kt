package com.aires.murilo.vuziqtest.ui.albums


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.aires.murilo.vuziqtest.ui.main.MainActivity
import com.aires.murilo.vuziqtest.R
import com.aires.murilo.vuziqtest.ui.albums.mvp.AlbumsMvpPresenter
import com.aires.murilo.vuziqtest.ui.albums.mvp.AlbumsMvpView
import com.aires.murilo.vuziqtest.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_albums.*
import javax.inject.Inject

class AlbumsFragment : BaseFragment(), AlbumsMvpView {

    @Inject
    lateinit var mPresenter: AlbumsMvpPresenter<AlbumsMvpView>

    private lateinit var mAdapter: AlbumsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val component = getActivityComponent()
        component?.inject(this)
        mPresenter.onAttach(this)

        return inflater.inflate(R.layout.fragment_albums, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = AlbumsAdapter(mPresenter)
        recyclerAlbums.layoutManager = LinearLayoutManager(getBaseActivity())
        recyclerAlbums.adapter = mAdapter
        mPresenter.onViewReady()
    }

    override fun onResume() {
        super.onResume()
        (getBaseActivity() as MainActivity).setToolbarTitle(getString(R.string.your_albums))
    }

    override fun onDetach() {
        super.onDetach()
        mPresenter.onDetach()
    }


    override fun notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged()
    }

    override fun showSelectedAlbumFragment(parameterKey: String, parameterValue: Long) {
        (getBaseActivity() as MainActivity).showSelectedAlbumFragment(Bundle().apply {
            putLong(
                parameterKey,
                parameterValue
            )
        })
    }
}
