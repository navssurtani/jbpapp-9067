// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation (1.1.2_01, build R40)
// Generated source version: 1.1.2

package org.jboss.test.webservice.admindevel._arrays.org.jboss.test.webservice.admindevel;


public class HelloObjArray {
    private org.jboss.test.webservice.admindevel.HelloObj[] value;
    
    public HelloObjArray() {
    }
    
    public HelloObjArray(org.jboss.test.webservice.admindevel.HelloObj[] sourceArray) {
        value = sourceArray;
    }
    
    public void fromArray(org.jboss.test.webservice.admindevel.HelloObj[] sourceArray) {
        this.value = sourceArray;
    }
    
    public org.jboss.test.webservice.admindevel.HelloObj[] toArray() {
        return value;
    }
    
    public org.jboss.test.webservice.admindevel.HelloObj[] getValue() {
        return value;
    }
    
    public void setValue(org.jboss.test.webservice.admindevel.HelloObj[] value) {
        this.value = value;
    }
}