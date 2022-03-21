package org.supla.android.charts

/*
 Copyright (C) AC SOFTWARE SP. Z O.O.

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

import android.content.Context
import android.widget.TextView

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.highlight.Highlight

import org.supla.android.R

class EMMarkerView(private val ihelper: IncrementalMeterChartHelper, 
                   context: Context): 
    IncrementalMeterMarkerView(ihelper, context, R.layout.em_chart_helper) {
    private val tvSelLabel: TextView
    private val tvSelValue1: TextView
    private val tvSelValue2: TextView

    init {
        tvSelLabel = findViewById(R.id.tvmSelLabel)
        tvSelValue1 = findViewById(R.id.tvmSelValue1)
        tvSelValue2 = findViewById(R.id.tvmSelValue2)
    }

    override public fun refreshContent(e: Entry, h: Highlight) {
        super.refreshContent(e, h)

        val si = h.getStackIndex()
        val lbl = getContext().getText(R.string.em_chart_phase_n).toString().format(si + 1)
        tvSelLabel.setText(lbl)

        if(e is BarEntry) {
            val pv = e.getYVals()[si]
            val cur = ihelper.getCurrency() ?: ""
            tvSelValue1.setText(String.format("%.2f " + cur, 
                                              pv * ihelper.getPricePerUnit()))
            tvSelValue2.setText(String.format("%.2f" + getString(ihelper.getUnit()),
                                              pv))
        }
    }

}
