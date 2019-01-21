package com.manuflowers.notificationsfirebase

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import com.manuflowers.notificationsfirebase.utils.*
import kotlinx.android.synthetic.main.notification_dialog.*

class NotificationModal : DialogFragment() {

    companion object {
        fun newInstance(
            title: String,
            image: String,
            description: String,
            link: String
        ): NotificationModal {
            val frag = NotificationModal()
            val args = Bundle()
            args.putSerializable(TAG_MODAL_TITLE, title)

            if (!description.isBlank()) args.putSerializable(TAG_MODAL_DESCRIPTION, description)
            if (!image.isBlank()) args.putSerializable(TAG_MODAL_IMAGE, image)
            if (!link.isBlank()) args.putSerializable(TAG_MODAL_LINK, link)

            frag.arguments = args
            return frag
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater
        .inflate(R.layout.notification_dialog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)

        tvTitleModal.text = arguments!!.getString(TAG_MODAL_TITLE)

        if (arguments!!.containsKey(TAG_MODAL_DESCRIPTION)) {
            tvDescriptionModal.visibility = View.VISIBLE
            tvDescriptionModal.text = arguments!!.getString(TAG_MODAL_DESCRIPTION)
        }

        if (arguments!!.containsKey(TAG_MODAL_IMAGE)) {
            imgModal.visibility = View.VISIBLE
            imgModal.loadUrlWithPicasso(
                arguments!!.getString(TAG_MODAL_IMAGE)!!,
                R.drawable.neo_place_holder
            )
        }

        if (arguments!!.containsKey(TAG_MODAL_LINK)) {
            val link = arguments!!.getString(TAG_MODAL_LINK)
            val i = Intent(Intent.ACTION_VIEW)
            i.data = link!!.toUri()
            btnLinkModal.setOnClickListener {
                startActivity(i)
            }
            btnLinkModal.visibility = View.VISIBLE
        }
    }
}