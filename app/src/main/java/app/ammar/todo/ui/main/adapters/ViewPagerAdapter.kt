package app.ammar.todo.ui.main.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.*


class ViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    data class FragMetaData(val fragment: Fragment, val title: String)


    private val fragmentList = ArrayList<FragMetaData>()


    override fun getCount(): Int = fragmentList.size

    override fun getItem(position: Int): Fragment = fragmentList[position].fragment

    override fun getPageTitle(position: Int): CharSequence = fragmentList[position].title


    fun add(fragment: Fragment, title: String) = fragmentList.add(FragMetaData(fragment, title))

}
