const API_URL = 'http://localhost:8080/api/policies';

let isEditMode = false;
let currentEditId = null;
let allPolicies = [];

// DOM Elements
const policyForm = document.getElementById('policy-form');
const formTitle = document.getElementById('form-title');
const submitBtn = document.getElementById('submit-btn');
const cancelBtn = document.getElementById('cancel-btn');
const searchBtn = document.getElementById('search-btn');
const searchInput = document.getElementById('search-input');
const filterStatus = document.getElementById('filter-status');
const refreshBtn = document.getElementById('refresh-btn');
const policyTbody = document.getElementById('policy-tbody');
const messageDiv = document.getElementById('message');

// Initialize
document.addEventListener('DOMContentLoaded', () => {
    checkAuth();
    loadPolicies();
    setupEventListeners();
    setupNavigation();
});

function checkAuth() {
    const user = localStorage.getItem('user');
    if (!user) {
        window.location.href = 'login.html';
        return;
    }
    
    const userData = JSON.parse(user);
    const userDisplay = document.getElementById('user-display');
    if (userDisplay) {
        userDisplay.textContent = userData.username;
    }
}

function logout() {
    localStorage.removeItem('user');
    window.location.href = 'login.html';
}

// Navigation
function setupNavigation() {
    const navItems = document.querySelectorAll('.nav-item');
    navItems.forEach(item => {
        item.addEventListener('click', (e) => {
            e.preventDefault();
            const section = item.dataset.section;
            showSection(section);
            
            navItems.forEach(nav => nav.classList.remove('active'));
            item.classList.add('active');
        });
    });
}

function showSection(sectionName) {
    const sections = document.querySelectorAll('.content-section');
    sections.forEach(section => section.classList.remove('active'));
    
    const targetSection = document.getElementById(`${sectionName}-section`);
    if (targetSection) {
        targetSection.classList.add('active');
    }
    
    // Update page title
    const pageTitle = document.getElementById('page-title');
    const pageSubtitle = document.getElementById('page-subtitle');
    
    switch(sectionName) {
        case 'dashboard':
            pageTitle.textContent = 'Dashboard';
            pageSubtitle.textContent = 'Overview of your insurance policies';
            updateDashboard();
            break;
        case 'add-policy':
            pageTitle.textContent = isEditMode ? 'Edit Policy' : 'Add New Policy';
            pageSubtitle.textContent = isEditMode ? 'Update policy information' : 'Create a new insurance policy';
            break;
        case 'all-policies':
            pageTitle.textContent = 'All Policies';
            pageSubtitle.textContent = 'Manage and view all insurance policies';
            break;
    }
}

function setupEventListeners() {
    policyForm.addEventListener('submit', handleSubmit);
    cancelBtn.addEventListener('click', resetForm);
    searchBtn.addEventListener('click', handleSearch);
    filterStatus.addEventListener('change', handleFilter);
    refreshBtn.addEventListener('click', loadPolicies);
}

async function handleSubmit(e) {
    e.preventDefault();
    
    const policyData = {
        policyHolderName: document.getElementById('holder-name').value,
        policyType: document.getElementById('policy-type').value,
        premiumAmount: parseFloat(document.getElementById('premium').value),
        startDate: document.getElementById('start-date').value,
        endDate: document.getElementById('end-date').value,
        status: document.getElementById('status').value
    };
    
    try {
        if (isEditMode) {
            await updatePolicy(currentEditId, policyData);
        } else {
            await createPolicy(policyData);
        }
    } catch (error) {
        showMessage('Operation failed: ' + error.message, 'error');
    }
}

async function createPolicy(policyData) {
    const response = await fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(policyData)
    });
    
    if (!response.ok) {
        const error = await response.json();
        throw new Error(error.error || 'Failed to create policy');
    }
    
    showMessage('Policy created successfully!', 'success');
    resetForm();
    loadPolicies();
}

async function updatePolicy(id, policyData) {
    const response = await fetch(`${API_URL}/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(policyData)
    });
    
    if (!response.ok) {
        const error = await response.json();
        throw new Error(error.error || 'Failed to update policy');
    }
    
    showMessage('Policy updated successfully!', 'success');
    resetForm();
    loadPolicies();
}

async function loadPolicies() {
    try {
        const response = await fetch(API_URL);
        allPolicies = await response.json();
        renderPolicies(allPolicies);
        updateDashboard();
    } catch (error) {
        showMessage('Failed to load policies', 'error');
    }
}

function updateDashboard() {
    const total = allPolicies.length;
    const active = allPolicies.filter(p => p.status === 'Active').length;
    const expired = allPolicies.filter(p => p.status === 'Expired').length;
    const cancelled = allPolicies.filter(p => p.status === 'Cancelled').length;
    
    document.getElementById('total-policies').textContent = total;
    document.getElementById('active-policies').textContent = active;
    document.getElementById('expired-policies').textContent = expired;
    document.getElementById('cancelled-policies').textContent = cancelled;
    
    // Show recent policies
    const recentPolicies = allPolicies.slice(0, 5);
    const recentContainer = document.getElementById('recent-policies');
    
    if (recentPolicies.length === 0) {
        recentContainer.innerHTML = '<div class="empty-state"><span class="empty-icon">📋</span><p>No policies yet</p></div>';
    } else {
        recentContainer.innerHTML = recentPolicies.map(policy => `
            <div class="policy-item">
                <div class="policy-info">
                    <h4>${policy.policyHolderName}</h4>
                    <p>${policy.policyType} - $${policy.premiumAmount.toFixed(2)}</p>
                </div>
                <span class="policy-badge badge-${policy.status.toLowerCase()}">${policy.status}</span>
            </div>
        `).join('');
    }
}

function renderPolicies(policies) {
    if (policies.length === 0) {
        policyTbody.innerHTML = '<tr><td colspan="8" class="no-data">No policies found.</td></tr>';
        return;
    }
    
    policyTbody.innerHTML = policies.map(policy => {
        const isExpired = policy.status === 'Expired';
        const rowClass = isExpired ? 'class="expired"' : '';
        
        return `
            <tr ${rowClass}>
                <td>${policy.id}</td>
                <td>${policy.policyHolderName}</td>
                <td>${policy.policyType}</td>
                <td>$${policy.premiumAmount.toFixed(2)}</td>
                <td>${policy.startDate}</td>
                <td>${policy.endDate}</td>
                <td>${policy.status}</td>
                <td>
                    <button class="action-btn edit-btn" onclick="editPolicy(${policy.id})">Edit</button>
                    <button class="action-btn delete-btn" onclick="deletePolicy(${policy.id})">Delete</button>
                </td>
            </tr>
        `;
    }).join('');
}

async function editPolicy(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        const policy = await response.json();
        
        document.getElementById('holder-name').value = policy.policyHolderName;
        document.getElementById('policy-type').value = policy.policyType;
        document.getElementById('premium').value = policy.premiumAmount;
        document.getElementById('start-date').value = policy.startDate;
        document.getElementById('end-date').value = policy.endDate;
        document.getElementById('status').value = policy.status;
        
        isEditMode = true;
        currentEditId = id;
        formTitle.textContent = 'Update Policy';
        submitBtn.innerHTML = '<span>💾</span> Update Policy';
        
        showSection('add-policy');
        document.querySelector('[data-section="add-policy"]').classList.add('active');
        document.querySelector('[data-section="all-policies"]').classList.remove('active');
    } catch (error) {
        showMessage('Failed to load policy details', 'error');
    }
}

async function deletePolicy(id) {
    if (!confirm('Are you sure you want to delete this policy?')) {
        return;
    }
    
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });
        
        if (!response.ok) {
            throw new Error('Failed to delete policy');
        }
        
        showMessage('Policy deleted successfully!', 'success');
        loadPolicies();
    } catch (error) {
        showMessage('Failed to delete policy', 'error');
    }
}

async function handleSearch() {
    const searchTerm = searchInput.value.trim();
    
    if (!searchTerm) {
        loadPolicies();
        return;
    }
    
    try {
        const response = await fetch(`${API_URL}/search?name=${encodeURIComponent(searchTerm)}`);
        const policies = await response.json();
        renderPolicies(policies);
    } catch (error) {
        showMessage('Search failed', 'error');
    }
}

async function handleFilter() {
    const status = filterStatus.value;
    
    if (!status) {
        loadPolicies();
        return;
    }
    
    try {
        const response = await fetch(`${API_URL}/filter?status=${encodeURIComponent(status)}`);
        const policies = await response.json();
        renderPolicies(policies);
    } catch (error) {
        showMessage('Filter failed', 'error');
    }
}

function resetForm() {
    policyForm.reset();
    isEditMode = false;
    currentEditId = null;
    formTitle.textContent = 'Add New Policy';
    submitBtn.innerHTML = '<span>💾</span> Save Policy';
    showSection('dashboard');
    document.querySelector('[data-section="dashboard"]').classList.add('active');
    document.querySelector('[data-section="add-policy"]').classList.remove('active');
}

function showMessage(message, type) {
    messageDiv.textContent = message;
    messageDiv.className = `message ${type}`;
    
    setTimeout(() => {
        messageDiv.className = 'message';
    }, 3000);
}
