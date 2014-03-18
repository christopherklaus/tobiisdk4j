package tobii.lowlevel.sdk;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Array;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
/**
 * This struct information about an eye tracker extension.<br>
 * <i>native declaration : tobii_gaze_sdk/include/tobiigaze_data_types.h:509</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("TobiiGazeCore64") 
public class tobiigaze_extension extends StructObject {
	@Field(0) 
	public int protocol_version() {
		return this.io.getIntField(this, 0);
	}
	@Field(0) 
	public tobiigaze_extension protocol_version(int protocol_version) {
		this.io.setIntField(this, 0, protocol_version);
		return this;
	}
	@Field(1) 
	public int extension_id() {
		return this.io.getIntField(this, 1);
	}
	@Field(1) 
	public tobiigaze_extension extension_id(int extension_id) {
		this.io.setIntField(this, 1, extension_id);
		return this;
	}
	@Field(2) 
	public int realm() {
		return this.io.getIntField(this, 2);
	}
	@Field(2) 
	public tobiigaze_extension realm(int realm) {
		this.io.setIntField(this, 2, realm);
		return this;
	}
	/// C type : char[TOBIIGAZE_EXTENSION_NAME_MAX_SIZE]
	@Array({16}) 
	@Field(3) 
	public Pointer<Byte > name() {
		return this.io.getPointerField(this, 3);
	}
	public tobiigaze_extension() {
		super();
	}
	public tobiigaze_extension(Pointer pointer) {
		super(pointer);
	}
}