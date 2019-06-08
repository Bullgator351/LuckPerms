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

package me.lucko.luckperms.common.api.implementation;

import me.lucko.luckperms.api.context.ContextSetFactory;
import me.lucko.luckperms.api.context.ImmutableContextSet;
import me.lucko.luckperms.api.context.MutableContextSet;
import me.lucko.luckperms.common.api.context.ImmutableContextSetImpl;
import me.lucko.luckperms.common.api.context.MutableContextSetImpl;

import org.checkerframework.checker.nullness.qual.NonNull;

public class ApiContextSetFactory implements ContextSetFactory {
    public static final ApiContextSetFactory INSTANCE = new ApiContextSetFactory();

    private ApiContextSetFactory() {

    }

    @Override
    public ImmutableContextSet.@NonNull Builder immutableBuilder() {
        return new ImmutableContextSetImpl.BuilderImpl();
    }

    @Override
    public @NonNull ImmutableContextSet immutableOf(@NonNull String key, @NonNull String value) {
        return ImmutableContextSetImpl.of(key, value);
    }

    @Override
    public @NonNull ImmutableContextSet immutableOf(@NonNull String key1, @NonNull String value1, @NonNull String key2, @NonNull String value2) {
        return ImmutableContextSetImpl.of(key1, value1, key2, value2);
    }

    @Override
    public @NonNull ImmutableContextSet immutableEmpty() {
        return ImmutableContextSetImpl.EMPTY;
    }

    @Override
    public @NonNull MutableContextSet mutable() {
        return new MutableContextSetImpl();
    }
}
