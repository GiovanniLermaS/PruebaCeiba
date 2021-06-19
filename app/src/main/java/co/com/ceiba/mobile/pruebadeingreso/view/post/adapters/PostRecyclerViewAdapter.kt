package co.com.ceiba.mobile.pruebadeingreso.view.post.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.databinding.PostListItemBinding
import co.com.ceiba.mobile.pruebadeingreso.db.model.Post

class PostRecyclerViewAdapter : RecyclerView.Adapter<PostRecyclerViewAdapter.MyViewHolder>() {

    private var posts = ArrayList<Post>()

    fun setPostsData(posts: ArrayList<Post>) {
        this.posts = posts
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PostListItemBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

    class MyViewHolder(private val binding: PostListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.postData = post
            binding.executePendingBindings()
        }
    }
}