// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation (1.1.3, build R1)
// Generated source version: 1.1.3

package org.jboss.test.bpel.ws.consumption.partner;

public interface TextTranslator extends java.rmi.Remote {
    public java.lang.String translate(java.lang.String text, java.lang.String sourceLanguage, java.lang.String targetLanguage) throws 
        org.jboss.test.bpel.ws.consumption.partner.types.TTextNotTranslatable, org.jboss.test.bpel.ws.consumption.partner.types.TDictionaryNotAvailable,  java.rmi.RemoteException;
    public void quoteTranslation(java.lang.String clientName, java.lang.String text, java.lang.String sourceLanguage, java.lang.String targetLanguage) throws 
         java.rmi.RemoteException;
    public org.jboss.test.bpel.ws.consumption.partner.types.TQuoteStatus getQuotationStatus(java.lang.String clientName) throws 
         java.rmi.RemoteException;
}
