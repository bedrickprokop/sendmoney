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
        val transfer = mData!![position]

        //TODO Usar framework de imagem - glide, etc
        //holder.bind.ivTransferPicture
        holder.bind.tvTransferName.text = StringUtil.truncate(transfer.name, 20)
        holder.bind.tvTransferPhone.text = transfer.phone
        holder.bind.tvTransferValue.text = StringUtil.convertToMoneyForm(transfer.value)
    }

    override fun getItemCount(): Int {
        return if (mData.isNullOrEmpty()) 0 else mData!!.size
    }

    fun setData(transferList: List<Transfer>) {
        mData = transferList
        notifyItemRangeInserted(0, transferList.size)
    }

    inner class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        var bind: ItemTransferHistoryBinding = DataBindingUtil.bind(view)!!
    }
}