package co.haptik.tabbedchat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import co.haptik.tabbedchat.R;
import co.haptik.tabbedchat.models.FavoriteObject;
import co.haptik.tabbedchat.models.MessageListObject;
import co.haptik.tabbedchat.models.MessageObject;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by vijayagnihotri on 16/08/16.
 */
public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.ViewHolder> {

    private HashMap<String, FavoriteObject> favMap = new HashMap<>();
    private ArrayList<FavoriteObject> favList = new ArrayList<>();
    Context context;

    public FavoriteListAdapter(MessageListObject messageListObject, Context context) {
        this.context = context;
        for (MessageObject message : messageListObject.messages) {
            FavoriteObject favoriteObject = favMap.get(message.username);
            if(favoriteObject != null) {
                favoriteObject.messageCount++;
                if(message.isFav) {
                    favoriteObject.favCount++;
                }
            } else {
                favoriteObject = new FavoriteObject();
                favoriteObject.name = message.name;
                favoriteObject.username = message.username;
                favoriteObject.imageUrl = message.imageUrl;
                favoriteObject.messageCount = 1;
                if(message.isFav) {
                    favoriteObject.favCount = 1;
                } else {
                    favoriteObject.favCount = 0;
                }
            }
            favMap.put(message.username, favoriteObject);
        }
        for(String key : favMap.keySet()) {
            favList.add(favMap.get(key));
        }
        favList.size();
    }

    @Override
    public FavoriteListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favorite_row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteListAdapter.ViewHolder viewHolder, int position) {
        FavoriteObject favoriteObject = favList.get(position);
        viewHolder.chatFavName.setText(favoriteObject.name);
        viewHolder.chatMessageCount.setText(favoriteObject.messageCount.toString());
        viewHolder.chatFavCount.setText(favoriteObject.favCount.toString());
        if(!favoriteObject.imageUrl.isEmpty()) {
            Picasso.with(context).load(favoriteObject.imageUrl).placeholder(R.drawable.placeholder).resize(60, 60).noFade().into(viewHolder.chatProfilePic);
        }
    }

    @Override
    public int getItemCount() {
        return favList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView chatFavName;
        private CircleImageView chatProfilePic;
        private TextView chatMessageCount;
        private TextView chatFavCount;
        public ViewHolder(View view) {
            super(view);
            chatFavName = (TextView)view.findViewById(R.id.fav_name_text);
            chatProfilePic = (CircleImageView)view.findViewById(R.id.fav_profile_pic);
            chatMessageCount = (TextView)view.findViewById(R.id.fav_message_count_text);
            chatFavCount = (TextView)view.findViewById(R.id.fav_count_text);
        }
    }
}
