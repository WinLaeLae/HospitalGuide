package mm.com.fairway.hospitalguide.ui.weblink

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_hosptal_web_link.*
import mm.com.fairway.hospitalguide.R


class HosptalWebLinkFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hosptal_web_link, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var dataItem = arguments?.let {
            HosptalWebLinkFragmentArgs.fromBundle(it)
        }
        var url = dataItem!! .webLink
     detailWeb .loadUrl(url)
        val webSettings = detailWeb.settings
        webSettings.javaScriptEnabled= true
        if (detailWeb!!.canGoBack()){
            detailWeb.goBack()
        }
    }

}