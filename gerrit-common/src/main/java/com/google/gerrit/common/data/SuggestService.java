// Copyright (C) 2008 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.gerrit.common.data;

import com.google.gerrit.reviewdb.client.Change;
import com.google.gerrit.reviewdb.client.Project;
import com.google.gwtjsonrpc.common.AsyncCallback;
import com.google.gwtjsonrpc.common.RemoteJsonService;
import com.google.gwtjsonrpc.common.RpcImpl;
import com.google.gwtjsonrpc.common.RpcImpl.Version;

import java.util.List;

@RpcImpl(version = Version.V2_0)
public interface SuggestService extends RemoteJsonService {
  void suggestAccount(String query, Boolean enabled, int limit,
      AsyncCallback<List<AccountInfo>> callback);

  /**
   * @see #suggestAccountGroupForProject(com.google.gerrit.reviewdb.client.Project.NameKey, String, int, AsyncCallback)
   */
  @Deprecated
  void suggestAccountGroup(String query, int limit,
      AsyncCallback<List<GroupReference>> callback);

  void suggestAccountGroupForProject(Project.NameKey project, String query,
      int limit, AsyncCallback<List<GroupReference>> callback);

  /**
   * @see #suggestChangeReviewer(com.google.gerrit.reviewdb.client.Change.Id, String, int, AsyncCallback)
   */
  @Deprecated
  void suggestReviewer(Project.NameKey project, String query, int limit,
      AsyncCallback<List<ReviewerInfo>> callback);

  /**
   * Suggests reviewers. A reviewer can be a user or a group. Inactive users,
   * the system groups {@code SystemGroupBackend#ANONYMOUS_USERS} and
   * {@code SystemGroupBackend#REGISTERED_USERS} and groups that have more than
   * the configured {@code addReviewer.maxAllowed} members are not suggested as
   * reviewers.
   * @param changeId the change for which reviewers should be suggested
   */
  void suggestChangeReviewer(Change.Id changeId, String query, int limit,
      AsyncCallback<List<ReviewerInfo>> callback);
}
