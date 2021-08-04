/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.walker.war.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.walker.war.ui.dashboard.DashboardFragment
import com.walker.war.ui.home.HomeFragment
import com.walker.war.ui.notifications.NotificationsFragment
import com.sankuai.waimai.router.Router


const val HOME_PAGE_INDEX = 0
const val DASH_PAGE_INDEX = 1
const val NOTIFICATION_PAGE_INDEX = 2

class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        HOME_PAGE_INDEX to { HomeFragment() },
        DASH_PAGE_INDEX to { DashboardFragment() },
        NOTIFICATION_PAGE_INDEX to { NotificationsFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        if (position == 2) {
            return Router.getService(
                Fragment::class.java,
                "NotificationsFragment"
            )
        }
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}
