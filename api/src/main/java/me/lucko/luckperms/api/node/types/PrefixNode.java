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

package me.lucko.luckperms.api.node.types;

import me.lucko.luckperms.api.LuckPermsProvider;
import me.lucko.luckperms.api.node.Node;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * A sub-type of {@link Node} used to store prefix assignments.
 *
 * @since 4.2
 */
public interface PrefixNode extends ChatMetaNode<PrefixNode, PrefixNode.Builder> {

    /**
     * Gets the actual prefix string.
     *
     * @return the prefix
     */
    @NonNull String getPrefix();

    /**
     * TODO
     *
     * @return
     */
    static @NonNull Builder builder() {
        return LuckPermsProvider.get().getNodeBuilderRegistry().forPrefix();
    }

    /**
     * TODO
     *
     * @param prefix
     * @param priority
     * @return
     */
    static @NonNull Builder builder(@NonNull String prefix, int priority) {
        return builder().prefix(prefix).priority(priority);
    }

    /**
     * TODO
     */
    interface Builder extends ChatMetaNode.Builder<PrefixNode, Builder> {

        /**
         * TODO
         *
         * @param prefix
         * @return
         */
        @NonNull Builder prefix(@NonNull String prefix);

    }

}