package co.com.ceiba.mobile.pruebadeingreso.view.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.databinding.UserListItemBinding
import co.com.ceiba.mobile.pruebadeingreso.db.model.User
import co.com.ceiba.mobile.pruebadeingreso.view.main.interfaces.OnClickPost
import java.util.*
import kotlin.collections.ArrayList

class MainRecyclerViewAdapter(private val onClickPost: OnClickPost) :
        RecyclerView.Adapter<MainRecyclerViewAdapter.MyViewHolder>(), Filterable {

    private var users = ArrayList<User>()
    private var temporalUsers = ArrayList<User>()

    fun setUsersData(users: ArrayList<User>) {
        this.users = users
        this.temporalUsers = users
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UserListItemBinding.inflate(layoutInflater)
        return MyViewHolder(binding, onClickPost)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                filterResults.values = if (charSequence.toString().isEmpty()) {
                    temporalUsers
                } else {
                    temporalUsers.filter { it.name?.toLowerCase(Locale.ROOT)?.contains(charSequence.toString().toLowerCase(Locale.ROOT))!! } as ArrayList<User>
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                users = results?.values as ArrayList<User>
                notifyDataSetChanged()
            }

        }
    }

    class MyViewHolder(private val binding: UserListItemBinding, private val onClickPost: OnClickPost) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.userData = user
            binding.executePendingBindings()
            binding.btnViewPost.setOnClickListener { onClickPost.goPostActivity(user) }
        }
    }
}