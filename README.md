# CustomComponent
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


