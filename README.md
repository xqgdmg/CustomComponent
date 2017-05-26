# CustomComponent
View 的大概情况
public class MyView extends View {

	private static final String TAG = "MyView";

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	/**
	 * 父容器拿到孩子在布局中配置的宽高（layout_width, layout_height）
	 * 将孩子的申请的宽高打包成widthMeasureSpec（对孩子宽度的期望） heightMeasureSpec（对孩子高度的期望），
	 * 父容器调用孩子的measure方法，传入对孩子宽高的期望 -> onMesure
	 * 
	 * RelativeLayout会调用MyView measure方法 - 》 oMeasure
	 * 
	 * @param widthMeasureSpec是父容器（RelativeLayout）对MyView的宽度的期望
	 * @param heightMeasureSpec是父容器(RelativeLaoyut)对MyView的高度的期望
	 * 
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//int widthMeasureSpec 32位二进制
		//32 前两位 和后30位
		//前两位表示模式 mode
		//public static final int UNSPECIFIED = 0 << MODE_SHIFT; 父容器对孩子没有任何大小要求
		//public static final int EXACTLY     = 1 << MODE_SHIFT; 父容器对孩子有确切的大小要求，大小就是后30位数据
		//public static final int AT_MOST     = 2 << MODE_SHIFT; 父容器指定的孩子的最大值 ，最大值就是后面的30位
		
		//后30位表示大小 size 像素的个数
		//拆出模式 和大小
//		int mode = MeasureSpec.getMode(measureSpec);
//		int size = MeasureSpec.getSize(measureSpec);
		
		int mode = MeasureSpec.getMode(widthMeasureSpec) >> 30;
		
		Log.d(TAG, "mode " + mode
				+ " size " + MeasureSpec.getSize(widthMeasureSpec));
		//默认使用布局里面的参数，设置View的测量的宽高,使用父容器对我宽高期望
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//setMeasuredDimension决定View的宽和高
//		setMeasuredDimension(50, 50);
	}

}


ViewGroup 的大概情况
public class MyViewGroup extends ViewGroup {

	private static final String TAG = "MyViewGroup";
	private View mChild;
	
	public MyViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		mChild = getChildAt(0);
		//看看孩子测量的宽高
		int childWidth = mChild.getMeasuredWidth();
		int childHeight = mChild.getMeasuredHeight();
		
//		Log.d(TAG, "child width height " + childWidth + " " + childHeight);
		
		//去获取孩子向我申请的宽高
		LayoutParams layoutParams = mChild.getLayoutParams();
		int width = layoutParams.width;
		int height = layoutParams.height;
		
		
		//作为一个父容器，有责任去测量孩子
		//传入对孩子宽高的期望
		int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);//组装measurespec
		int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);//组装measurespec
		//测量孩子
		mChild.measure(childWidthMeasureSpec, childHeightMeasureSpec);
//		Log.d(TAG, "child width height " + childWidth + " " + childHeight);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		//布局孩子
		//指定孩子上下左右的位置
		int left = 30;
		int top = 20;
		int right = left + mChild.getMeasuredWidth();//获取测量后的宽高
		int bottom = top + mChild.getMeasuredHeight();
		
		Log.d(TAG, "child get width " + mChild.getWidth());//获取布局后的宽高
		mChild.layout(left, top, right, bottom);
		Log.d(TAG, "child get width " + mChild.getWidth());//获取布局后的宽高
	}

}


