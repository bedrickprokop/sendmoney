package br.com.example.sendmoney.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.example.sendmoney.R
import br.com.example.sendmoney.databinding.ItemTransferHistoryBinding
import br.com.example.sendmoney.model.entity.Transfer
import br.com.example.sendmoney.util.MoneyUtil
import br.com.example.sendmoney.util.StringUtil

class TransferHistoryAdapter(
    private val mContext: Context,
    private var mData: List<Transfer>?
) : RecyclerView.Adapter<TransferHistoryAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val bind = DataBindingUtil.inflate<ItemTransferHistoryBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_transfer_history,
            parent,
            false
        )
        return ItemViewHolder(bind.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val transfer = mData?.get(position)

        //TODO Usar framework de imagem - glide, etc
        //holder.bind.ivTransferPicture

        var phoneContentDescription =
            mContext.getString(R.string.item_transfer_history_mtv_transfer_phone_description)
        phoneContentDescription = String.format(phoneContentDescription, transfer?.phone)

        holder.bind.mtvTransferName.text = transfer?.name?.let { StringUtil.truncate(it, 20) }
        holder.bind.mtvTransferPhone.text = transfer?.phone
        holder.bind.mtvTransferPhone.contentDescription = phoneContentDescription
        holder.bind.mtvTransferValue.text = transfer?.value?.let { MoneyUtil.format(it.toDouble()) }
    }

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    fun setData(transferList: List<Transfer>) {
        mData = transferList
        notifyItemRangeInserted(0, transferList.size)
    }

    inner class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var bind: ItemTransferHistoryBinding = DataBindingUtil.bind(view)!!
    }
}