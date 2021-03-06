//package com.mytestapp;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class HeaderFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    private static final int TYPE_HEADER = 0;
//    private static final int TYPE_ITEM = 1;
//    private static final int TYPE_FOOTER = 2;
//
//    ArrayList<Generic> generics;
//    Context context;
//
//    public HeaderFooterAdapter(Context context, List<Generic> generics) {
//        this.context = context;
//        this.generics = (ArrayList<Generic>) generics;
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
//        if(viewType == TYPE_HEADER) {
//            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.header_item, parent, false);
//            return new HeaderViewHolder (v);
//        } else if(viewType == TYPE_FOOTER) {
//            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.footer_item, parent, false);
//            return new FooterViewHolder (v);
//        } else if(viewType == TYPE_ITEM) {
//            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.list_item, parent, false);
//            return new GenericViewHolder (v);
//        }
//        return null;
//    }
//
//    private Generic getItem (int position) {
//        return generics.get (position);
//    }
//
//
//    @Override
//    public void onBindViewHolder (RecyclerView.ViewHolder holder, int position) {
//        if(holder instanceof HeaderViewHolder) {
//            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
//            headerHolder.txtTitleHeader.setText ("Header");
//            headerHolder.txtTitleHeader.setOnClickListener (new View.OnClickListener () {
//                @Override
//                public void onClick (View view) {
//                    Toast.makeText (context, "Clicked Header", Toast.LENGTH_SHORT).show ();
//                }
//            });
//        } else if(holder instanceof FooterViewHolder) {
//            FooterViewHolder footerHolder = (FooterViewHolder) holder;
//            footerHolder.txtTitleFooter.setText ("Load More");
//            footerHolder.txtTitleFooter.setOnClickListener (new View.OnClickListener () {
//                @Override
//                public void onClick (View view) {
//                    Toast.makeText (context, "Clicked Footer", Toast.LENGTH_SHORT).show ();
//                }
//            });
//        } else if(holder instanceof GenericViewHolder) {
//            Generic currentItem = getItem (position - 1);
//            GenericViewHolder genericViewHolder = (GenericViewHolder) holder;
//            genericViewHolder.txtName.setText (currentItem.getName ());
//            genericViewHolder.txtPhone.setText (currentItem.getPhone ());
//        }
//    }
//
//    //    need to override this method
//    @Override
//    public int getItemViewType (int position) {
//        if(isPositionHeader (position)) {
//            return TYPE_HEADER;
//        } else if(isPositionFooter (position)) {
//            return TYPE_FOOTER;
//        }
//        return TYPE_ITEM;
//    }
//
//    private boolean isPositionHeader (int position) {
//        return position == 0;
//    }
//
//    private boolean isPositionFooter (int position) {
//        return position == generics.size () + 1;
//    }
//
//    @Override
//    public int getItemCount () {
//        return generics.size () + 2;
//    }
//
//    class FooterViewHolder extends RecyclerView.ViewHolder {
//        TextView txtTitleFooter;
//
//        public FooterViewHolder (View itemView) {
//            super (itemView);
//            this.txtTitleFooter = (TextView) itemView.findViewById (R.id.txtFooter);
//        }
//    }
//
//    class HeaderViewHolder extends RecyclerView.ViewHolder {
//        TextView txtTitleHeader;
//
//        public HeaderViewHolder (View itemView) {
//            super (itemView);
//            this.txtTitleHeader = (TextView) itemView.findViewById (R.id.txtHeader);
//        }
//    }
//
//    class GenericViewHolder extends RecyclerView.ViewHolder {
//        TextView txtName,txtPhone;
//
//        public GenericViewHolder (View itemView) {
//            super (itemView);
//            this.txtName = (TextView) itemView.findViewById (R.id.txtName);
//            this.txtPhone = (TextView) itemView.findViewById (R.id.txtPhone);
//        }
//    }
//}
