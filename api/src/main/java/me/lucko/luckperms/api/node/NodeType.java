/*
 * This file is part of LuckPerms, licensed under the MIT License.
 *
 *  Copyright (c) lucko (Luck) <luck@lucko.me>
 *  Copyright (c) contributors
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package me.lucko.luckperms.api.node;

import me.lucko.luckperms.api.node.types.ChatMetaNode;
import me.lucko.luckperms.api.node.types.DisplayNameNode;
import me.lucko.luckperms.api.node.types.InheritanceNode;
import me.lucko.luckperms.api.node.types.MetaNode;
import me.lucko.luckperms.api.node.types.PermissionNode;
import me.lucko.luckperms.api.node.types.PrefixNode;
import me.lucko.luckperms.api.node.types.RegexPermissionNode;
import me.lucko.luckperms.api.node.types.SuffixNode;
import me.lucko.luckperms.api.node.types.WeightNode;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Represents a type of meta
 */
public interface NodeType<T extends Node> {

    /**
     * 
     */
    NodeType<PermissionNode> PERMISSION = new SimpleNodeType<>("PERMISSION", n ->  n instanceof PermissionNode, n -> ((PermissionNode) n));

    /**
     * 
     */
    NodeType<RegexPermissionNode> REGEX_PERMISSION = new SimpleNodeType<>("REGEX_PERMISSION", n ->  n instanceof RegexPermissionNode, n -> ((RegexPermissionNode) n));

    /**
     * 
     */
    NodeType<InheritanceNode> INHERITANCE = new SimpleNodeType<>("INHERITANCE", n ->  n instanceof InheritanceNode, n -> ((InheritanceNode) n));

    /**
     * Represents a prefix
     */
    NodeType<PrefixNode> PREFIX = new SimpleNodeType<>("PREFIX", n ->  n instanceof PrefixNode, n -> ((PrefixNode) n));

    /**
     * Represents a suffix
     */
    NodeType<SuffixNode> SUFFIX = new SimpleNodeType<>("SUFFIX", n ->  n instanceof SuffixNode, n -> ((SuffixNode) n));

    /**
     * Represents a meta key-value pair
     */
    NodeType<MetaNode> META = new SimpleNodeType<>("META", n ->  n instanceof MetaNode, n -> ((MetaNode) n));

    /**
     * 
     */
    NodeType<WeightNode> WEIGHT = new SimpleNodeType<>("WEIGHT", n ->  n instanceof WeightNode, n -> ((WeightNode) n));

    /**
     * 
     */
    NodeType<DisplayNameNode> DISPLAY_NAME = new SimpleNodeType<>("DISPLAY_NAME", n ->  n instanceof DisplayNameNode, n -> ((DisplayNameNode) n));

    /**
     * Represents any chat meta type
     */
    NodeType<ChatMetaNode<?, ?>> CHAT_META = new SimpleNodeType<>("CHAT_META", n ->  n instanceof ChatMetaNode<?, ?>, n -> ((ChatMetaNode<?, ?>) n));

    /**
     * Represents any meta type
     */
    NodeType<Node> META_OR_CHAT_META = new SimpleNodeType<>("META_OR_CHAT_META", n ->  META.matches(n) || CHAT_META.matches(n), Function.identity());

    String name();

    /**
     * Returns if the passed node matches the type
     *
     * @param node the node to test
     * @return true if the node has the same type
     */
    boolean matches(Node node);

    /**
     * 
     * @param node
     * @return
     */
    T cast(Node node);

    default Optional<T> tryCast(Node node) {
        Objects.requireNonNull(node, "node");
        if (!matches(node)) {
            return Optional.empty();
        } else {
            return Optional.of(cast(node));
        }
    }

    default Predicate<Node> predicate() {
        return this::matches;
    }

    default Predicate<Node> predicate(Predicate<? super T> and) {
        return node -> matches(node) && and.test(cast(node));
    }

}
