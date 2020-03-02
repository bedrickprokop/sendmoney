package br.com.example.sendmoney.view.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.example.sendmoney.R
import br.com.example.sendmoney.databinding.ItemContactsBinding
import br.com.example.sendmoney.model.entity.Contact
import br.com.example.sendmoney.util.StringUtil


class ContactsAdapter(
    private val mContext: Context,
    private var mData: List<Contact>?,
    private val listener: (contact: Contact) -> Unit
) : RecyclerView.Adapter<ContactsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val bind = DataBindingUtil.inflate<ItemContactsBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_contacts,
            parent,
            false
        )
        return ItemViewHolder(bind.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val contact = mData!![position]

        //TODO Usar framework de imagem - glide, etc
        //holder.bind.ivContactPicture
        holder.bind.clContainer.setOnClickListener {
            listener(contact)
        }

        holder.bind.tvContactName.text = StringUtil.truncate(contact.name, 20)
        holder.bind.tvContactPhone.text = contact.phone
    }

    override fun getItemCount(): Int {
        return if (mData.isNullOrEmpty()) 0 else mData!!.size
    }

    fun setData(contactList: List<Contact>) {
        mData = contactList
        notifyItemRangeInserted(0, contactList.size)
    }

    inner class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        var bind: ItemContactsBinding = DataBindingUtil.bind(view)!!
    }
}