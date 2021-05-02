package app.ammar.todo.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.*

class ViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val fragmentList = ArrayList<FragMetaData>()

    override fun getCount(): Int = fragmentList.size

    override fun getItem(position: Int): Fragment = fragmentList[position].fragment

    override fun getPageTitle(position: Int): CharSequence = fragmentList[position].title


    fun addFragment(fragment: Fragment, title: String) =
        fragmentList.add(FragMetaData(fragment, title))


    data class FragMetaData(val fragment: Fragment, val title: String)
}