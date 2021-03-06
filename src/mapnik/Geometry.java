package mapnik;

public class Geometry {
	/**
	 * mapnik::geometry_type*
	 */
	private long ptr;
	
	/**
	 * Only ever alloc'd from native code
	 */
	private Geometry() {
	}
	
	/**
	 * Invalidate this reference
	 */
	void invalidate() {
		ptr=0;
	}
	
	public static final int
		TYPE_POINT=1,
		TYPE_LINESTRING=2,
		TYPE_POLYGON=3,
		TYPE_MULTIPOINT=4,
		TYPE_MULTILINESTRING=5,
		TYPE_MULTIPOLYGON=6;
	
	public native int getType();
	public native int getNumPoints();
	public native int getVertex(int pos, Coord c);
	public native Box2d getEnvelope();
	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("Geometry(").append(getType()).append(",[");
		Coord c=new Coord();
		for (int i=0; i<getNumPoints(); i++) {
			int code=getVertex(i, c);
			if (i>0) sb.append(",");
			sb.append(c.x);
			sb.append(" ");
			sb.append(c.y);
		}
		
		sb.append("])");
		return sb.toString();
	}
}
