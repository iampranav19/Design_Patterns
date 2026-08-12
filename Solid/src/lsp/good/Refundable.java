package lsp.good;

// A separate contract — only payment types that can honestly support refunds implement this.
public interface Refundable {
    void refund();
}