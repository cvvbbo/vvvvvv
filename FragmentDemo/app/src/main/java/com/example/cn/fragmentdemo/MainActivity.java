package com.example.cn.fragmentdemo;

import android.app.Fragment;
import android.content.Intent;
import android.media.Image;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class MainActivity extends  FragmentActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ViewPager viewPager;
    private LinearLayout ll_point;
    private TextView tv;
    private ArrayList<ImageView> imageViews = new ArrayList<>();
    private final int[] imageaaa = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d
    };
    private int preposition = 0;
    private final String[] imagetitle = {//视频中那个很长的文字imageDEscriptions
            "lalala1",
            "lalala2",
            "lalala3",
            "lalala4"

    };


     private boolean  isclick=true;

    /*private android.os.Handler h = new android.os.Handler() {//这里是设置自动广告滑动的

        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            switch (msg.what){
                case 1:
                    int item = viewPager.getCurrentItem() + 1;
                    viewPager.setCurrentItem(item);//这个先写，int item后写

                    h.sendEmptyMessageDelayed(1, 4000);
                    break;
                case 2:
                    isclick=true;
                    h.sendEmptyMessageDelayed(2,4000);
                    break;

            }

        }

    };*/

    android.os.Handler  h=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int item = viewPager.getCurrentItem() + 1;
            viewPager.setCurrentItem(item);//这个先写，int item后写

            h.sendEmptyMessageDelayed(0, 4000);

        }
    };


    android.os.Handler  h2=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isclick=true;
            h2.sendEmptyMessageDelayed(0,4000);
        }
    };

    //private int realPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        viewPager = (ViewPager) findViewById(R.id.Vp);
        tv = (TextView) findViewById(R.id.tv_title);
        ll_point = (LinearLayout) findViewById(R.id.ll_point);


        //imageViews=new ArrayList<>();//这里其实是实例化的后半个部分，前半个部分在开头
        for (int i = 0; i < imageaaa.length; i++) {
            ImageView imageView = new ImageView(this);//要调用图片资源就先要调用ImageView资源
            imageView.setBackgroundResource(imageaaa[i]);
            imageViews.add(imageView);//把数据添加到集合中。

            //添加广告条下面的点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_selector);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(16, 16);//这个16是像素
            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
                params.leftMargin = 8;
            }


            point.setLayoutParams(params);

            ll_point.addView(point);//这个addview的意思和前面的add差不多，意思是把指定的资源（自定义图片之类）添加到指定容器中

        }

        viewPager.setAdapter(new MyPageradpter());
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        tv.setText(imagetitle[preposition]);//这个是为了让第一个标题和xml中的标题同步
        //下面这个参数是为了定位到某个位置
        int item = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageViews.size();//要保证是5的整数倍，没有为什么。不然可能会出错
        viewPager.setCurrentItem(item);
        h.sendEmptyMessageDelayed(0, 4000);
        h2.sendEmptyMessageDelayed(0,4000);

    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }


        @Override
        public void onPageSelected(int position) {

            int realPosition = position % imageViews.size();
            tv.setText(imagetitle[realPosition]);
            ll_point.getChildAt(preposition).setEnabled(false);
            ll_point.getChildAt(realPosition).setEnabled(true);

            preposition = realPosition;

        }


        @Override
        public void onPageScrollStateChanged(int state) {

            if (state == ViewPager.SCROLL_STATE_DRAGGING) {//这个是拖拽状态
               // Log.e(TAG, "-----------SCROLL_STATE_DRAGGING拖拽");

            } else if (state == ViewPager.SCROLL_STATE_SETTLING) {//这个开始
               // Log.e(TAG, "-----------SCROLL_STATE_SETTLING开始");
            } else if (state == ViewPager.SCROLL_STATE_IDLE) {//这个是空闲状态
                //Log.e(TAG, "-----------SCROLL_STATE_IDLE空闲");

            }
        }

    }


    class MyPageradpter extends PagerAdapter {


        @Override
        public int getCount() {
            // return imageViews.size();//返回的是集合的总数
            return Integer.MAX_VALUE;
        }

        @Override//检查视图和对象是否相等
        public boolean isViewFromObject(View view, Object object) {
            if (view == object) {
                return true;
            } else {

                return false;
            }
        }

        @Override//这个默认只能创建两个，然后开始执行销毁
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
            //Log.e(TAG, "destroyItem==" + position + "----==object" + object);
        }


        /**
         * @param container 这个相当于viewpager
         * @param position  当前实例化页面的位置
         * @return 点击转跳fragment这个目前好像解决的办法很少，基本是通过Activity来转跳的。。
         * 解决方案1，可以用设置禁止点击然后，在下一个图片的时候再回复上一个的点击
         * <p>
         * 反正老师说基本都是用Activity来转跳，那可以试一下这样的转跳到一个空壳的Activity界面，这个Activity界面是
         * 那个fragment大全demo的那个第一个界面。在这个Activity界面直接绑定一个fragment界面！！！
         */
        @Override
        public Object instantiateItem(final ViewGroup container, final int position) {//这个是创建界面

            int realPosition = position % imageViews.size();//

            //下面是原来的
            final ImageView imageView = imageViews.get(realPosition);//因为在前面已经实例化过，这里直接获取就行了

            //下面是添加返回键的
            // imageView = imageViews.get(realPosition);

            container.addView(imageView);
            //Log.e(TAG, "instantiateItem==" + position + "----imageview==" + imageView);
            imageView.setOnTouchListener(new View.OnTouchListener() {//写这个是为了防止手指在点击图片的时候图片自己动了
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {//其实这下面的写好之后就已经完美了70%，80%了，会自动走，还能手动走，让他更完美就在onPageScrollStateChanged方法
                        case MotionEvent.ACTION_DOWN:
                            h.removeCallbacksAndMessages(null);//这个是移除所有消息
                            break;//还有一个问题很奇怪，为什么要用getAction()
                        case MotionEvent.ACTION_MOVE:
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            h.removeCallbacksAndMessages(null);
                            h.sendEmptyMessageDelayed(0, 4000);
                            break;
                        case MotionEvent.ACTION_UP:
                            h.removeCallbacksAndMessages(null);
                            h.sendEmptyMessageDelayed(0, 4000);
                            break;
                    }

                    return false;//想要点击有弹窗之类的效果就要把这里写为false
                }
            });



            imageView.setTag(imageaaa[realPosition]);///得到位置，某个图片的位置
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch ((int) v.getTag()) {
                        case R.drawable.a:
                            if (isclick) {
                                h.removeCallbacksAndMessages(null);
                                getSupportFragmentManager().
                                        beginTransaction().
                                        //如果不加下面这个语句是直接退出的！！！！
                                        addToBackStack(null).
                                        replace(R.id.aaa, new newfragment()).
                                        commit();
                                isclick=false;
                                Log.e("第一次的情况"," "+isclick);

                                h2.sendEmptyMessageDelayed(0,4000);

                            }

                            Log.e("发了消息之后情况"," "+isclick);

                            break;

                        case R.drawable.b:

                            if (isclick) {
                                Log.e("第二个fragment开始", isclick + " ");
                                getSupportFragmentManager().
                                        beginTransaction().
                                        addToBackStack(null).
                                        replace(R.id.aaa, new newfragment1()).
                                        commit();
                                isclick=false;
                            }
                            Log.e("第二个fragment结束", isclick + " ");

                            break;
                        case R.drawable.c:

                            getSupportFragmentManager().
                                    beginTransaction().
                                    addToBackStack(null).
                                    replace(R.id.aaa, new newfragment2()).
                                    commit();

                            break;
                        case R.drawable.d:

                            getSupportFragmentManager().
                                    beginTransaction().
                                    addToBackStack(null).
                                    replace(R.id.aaa, new newfragment3()).
                                    commit();
                            break;
                    }
                }
            });

            // imageView.setEnabled(true);






           /* imageView.setTag(R.drawable.a);
            Log.e(TAG, "setTag" + TAG);
            imageView.setTag(R.drawable.b);
            Log.e(TAG, "setTag" + TAG);
            imageView.setTag(R.drawable.c);
            Log.e(TAG, "setTag" + TAG);
            imageView.setTag(R.drawable.d);
            Log.e(TAG, "setTag" + TAG);*/

            return imageView;

        }


    }}




   /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            System.out.println("按下了back键   onKeyDown()");
           h.sendEmptyMessageDelayed(0,2000);
            //imageView.setEnabled(true);

            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }*/






