package com.mytestapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;


public class HeaderFooterRecyclerActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    Context con;
    private ImageView imageView;
    List<Generic> contacts = new ArrayList<Generic>();
    HeaderFooterAdapter adapter;
    int loadTo = 10;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        con = this;
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.deleteContact();
        getListItems ();

        for (int i = 0; i<=loadTo; i++){
            contacts.add(databaseHelper.getContact().get(i));
        }


        imageView = (ImageView)findViewById(R.id.imageView);
        Picasso.with(con).load(R.drawable.sadi).transform(new CircleTransform()).into(imageView);
        RecyclerView recyclerView = (RecyclerView) findViewById (R.id.list_recylclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new HeaderFooterAdapter(HeaderFooterRecyclerActivity.this, contacts);
        recyclerView.setLayoutManager (linearLayoutManager);
        recyclerView.setAdapter (adapter);
    }


    public ArrayList<Generic> getListItems () {
        ArrayList<Generic> generics = new ArrayList<Generic>();
        for (int i = 0; i < 20; i++) {
            Generic item = new Generic ();
            item.setName ("sadi" + i);
            item.setPhone ("+0172335972" + i);
            generics.add (item);

            databaseHelper.addContact(new Generic(generics.get(i).getName(),generics.get(i).getPhone()));
        }
        return generics;
    }


    public class HeaderFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static final int TYPE_HEADER = 0;
        private static final int TYPE_ITEM = 1;
        private static final int TYPE_FOOTER = 2;

        ArrayList<Generic> generics;
        Context context;

        public HeaderFooterAdapter(Context context, List<Generic> generics) {
            this.context = context;
            this.generics = (ArrayList<Generic>) generics;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
            if(viewType == TYPE_HEADER) {
                View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.header_item, parent, false);
                return new HeaderFooterAdapter.HeaderViewHolder(v);
            } else if(viewType == TYPE_FOOTER) {
                View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.footer_item, parent, false);
                return new HeaderFooterAdapter.FooterViewHolder(v);
            } else if(viewType == TYPE_ITEM) {
                View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.list_item, parent, false);
                return new HeaderFooterAdapter.GenericViewHolder(v);
            }
            return null;
        }

        private Generic getItem (int position) {
            return generics.get (position);
        }


        @Override
        public void onBindViewHolder (RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof HeaderFooterAdapter.HeaderViewHolder) {
                HeaderFooterAdapter.HeaderViewHolder headerHolder = (HeaderFooterAdapter.HeaderViewHolder) holder;
                headerHolder.txtTitleHeader.setText ("Header");
                headerHolder.txtTitleHeader.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick (View view) {
                        Toast.makeText (context, "Clicked Header", Toast.LENGTH_SHORT).show ();
                    }
                });
            } else if(holder instanceof HeaderFooterAdapter.FooterViewHolder) {
                final HeaderFooterAdapter.FooterViewHolder footerHolder = (HeaderFooterAdapter.FooterViewHolder) holder;
               // footerHolder.txtTitleFooter.setText ("Load More");
                footerHolder.txtTitleFooter.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick (View view) {
                        loadTo+=10;
                        Log.e("loadTo",""+loadTo);
                        if(databaseHelper.getContact().size()>=loadTo){
                            for (int i = 0; i<loadTo; i++){
                                contacts.add(databaseHelper.getContact().get(i));
                                adapter.notifyDataSetChanged();
                            }

                        }else {
                            Log.e("loadTo else",""+loadTo);
                            footerHolder.txtTitleFooter.setText ("No More data");
                            //for (int i = 0; i<databaseHelper.getContact().size(); i++){
                                contacts=databaseHelper.getContact();
                                adapter.notifyDataSetChanged();
                            //}
                        }

                    }
                });
            } else if(holder instanceof HeaderFooterAdapter.GenericViewHolder) {
                Generic currentItem = getItem (position - 1);
                HeaderFooterAdapter.GenericViewHolder genericViewHolder = (HeaderFooterAdapter.GenericViewHolder) holder;
                genericViewHolder.txtName.setText (currentItem.getName ());
                genericViewHolder.txtPhone.setText (currentItem.getPhone ());
            }
        }

        //    need to override this method
        @Override
        public int getItemViewType (int position) {
            if(isPositionHeader (position)) {
                return TYPE_HEADER;
            } else if(isPositionFooter (position)) {
                return TYPE_FOOTER;
            }
            return TYPE_ITEM;
        }

        private boolean isPositionHeader (int position) {
            return position == 0;
        }

        private boolean isPositionFooter (int position) {
            return position == generics.size () + 1;
        }

        @Override
        public int getItemCount () {
            return generics.size () + 2;
        }

        class FooterViewHolder extends RecyclerView.ViewHolder {
            TextView txtTitleFooter;

            public FooterViewHolder (View itemView) {
                super (itemView);
                this.txtTitleFooter = (TextView) itemView.findViewById (R.id.txtFooter);
            }
        }

        class HeaderViewHolder extends RecyclerView.ViewHolder {
            TextView txtTitleHeader;

            public HeaderViewHolder (View itemView) {
                super (itemView);
                this.txtTitleHeader = (TextView) itemView.findViewById (R.id.txtHeader);
            }
        }

        class GenericViewHolder extends RecyclerView.ViewHolder {
            TextView txtName,txtPhone;

            public GenericViewHolder (View itemView) {
                super (itemView);
                this.txtName = (TextView) itemView.findViewById (R.id.txtName);
                this.txtPhone = (TextView) itemView.findViewById (R.id.txtPhone);
            }
        }
    }


}
