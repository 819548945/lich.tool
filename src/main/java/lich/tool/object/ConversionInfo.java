package lich.tool.object;
/**
 * field type change Rules
 * @author liuch
 * @param <T> original type
 * @param <Z> target type
 */
public abstract class ConversionInfo <T,Z>{
		/**
		 * Convert t to Z
		 * @param t original data
		 * @return target data
		 */
		public abstract Z exec(T t);
}
