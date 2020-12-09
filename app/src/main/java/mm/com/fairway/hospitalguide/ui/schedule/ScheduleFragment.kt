package mm.com.fairway.hospitalguide.ui.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_schedule.*
import mm.com.fairway.hospitalguide.R
import mm.com.fairway.hospitalguide.adapter.ScheduleAdapter
import mm.com.fairway.hospitalguide.viewModel.HospitalGuideViewModel

class ScheduleFragment : Fragment() {
    private lateinit var scheduleViewModel: HospitalGuideViewModel
    private lateinit var scheduleAdatper: ScheduleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onResume() {
        super.onResume()
        scheduleViewModel.setScheduleList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scheduleViewModel = ViewModelProvider(this).get(HospitalGuideViewModel::class.java)
        scheduleAdatper = ScheduleAdapter()
recyclerView_schedul.apply {
    layoutManager=LinearLayoutManager(context)
    adapter= scheduleAdatper
}
        scheduleViewModel.getScheduleList().observe(viewLifecycleOwner, Observer {   schedule->
        scheduleAdatper.updateSchedule( schedule.schedule as ArrayList<Schedule>)})
        
         }
}